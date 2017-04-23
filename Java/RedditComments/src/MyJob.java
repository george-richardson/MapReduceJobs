import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyJob {

    public static class  MyMapper
            extends Mapper<Object, Text, Text, IntWritable>{

        private final List<String> catWords = Arrays.asList("cat", "kitten", "kittycat", "kitty", "meow", "purr");
        private final List<String> dogWords = Arrays.asList("dog", "doggy", "puppy", "doggo", "pupper", "woof");

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            //convert from json
            RedditComment comment;
            try {
                comment = new Gson().fromJson(value.toString(), RedditComment.class);
            } catch (JsonSyntaxException exception) {
                return;
            }

            //extract body
            String subreddit = comment.getSubreddit();
            String[] words = comment.getBody().toLowerCase().split("\\P{L}+");
            int score = 0;

            for (String word : words) {
                if (catWords.contains(word)) {
                    score++;
                    break;
                }
                if (dogWords.contains(word)) score--;
            }

            context.write(new Text(subreddit), new IntWritable(score));
        }
    }

    public static class MyReducer
            extends Reducer<Text,IntWritable,Text,DoubleWritable> {
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            //average the scores
            int total = 0;
            int count = 0;
            for (IntWritable val : values) {
                count ++;
                total += val.get();
            }
            double average = (double) total / (double) count;
            context.write(key, new DoubleWritable(average));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "reddit comments");
        job.setJarByClass(MyJob.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}