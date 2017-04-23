import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyJob {

    public static class MyMapper
            extends Mapper<Object, Text, IntWritable, IntWritable>{

        private final double centerLongitudeRadians = -0.00070130820003, centerLatitudeRadians = 0.899246234849;

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\\t");
            if (fields.length != 19) return;
            String elevationString = fields[16];
            String longitudeString = fields[5];
            String latitudeString = fields[4];

            //attempt to parse the long and lat from the input
            double longitudeDecimal, latitudeDecimal;
            int elevation;
            try {
                longitudeDecimal= Double.parseDouble(longitudeString);
                latitudeDecimal = Double.parseDouble(latitudeString);
                elevation = Integer.parseInt(elevationString);
            } catch (NumberFormatException ex) {
                return;
            }

            //calculate the distance in meters
            //uses the haversine formula
            double latitudeRadians = toRadians(latitudeDecimal);
            double longitudeRadians = toRadians(longitudeDecimal);
            double latDifference = centerLatitudeRadians - latitudeRadians;
            double longDifference = centerLongitudeRadians - longitudeRadians;

            int radius = 6371000; //radius of earth in m
            double a = Math.sin(latDifference/2) * Math.sin(latDifference/2) + Math.cos(latitudeRadians) * Math.cos(centerLatitudeRadians) * Math.sin(longDifference/2) * Math.sin(longDifference/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = radius * c;

            //Bucket the distance into 100km ranges
            int bucket = (int) distance / 100000;

            //Return the bucket and elevation
            context.write(new IntWritable(bucket), new IntWritable(elevation));
        }
    }

    private static double toRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

    public static class MyReducer
            extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {
        public void reduce(IntWritable key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int highestSoFar = 0;
            for (IntWritable val : values) {
                if (val.get() > highestSoFar) highestSoFar = val.get();
            }
            context.write(key, new IntWritable(highestSoFar));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "nearby locations");
        job.setJarByClass(MyJob.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}