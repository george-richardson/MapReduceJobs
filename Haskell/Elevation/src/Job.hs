module Job
    ( MapKey,
      MapValue,
      ReduceKey,
      ReduceValue,
      mapper,
      reducer,
    ) where
import Data.List.Split

type MapKey = String
type MapValue = Int
type ReduceKey = String
type ReduceValue = Int

toRadians :: Double -> Double
toRadians d = d * (pi / 180)

mapper :: String -> [(MapKey, [MapValue])]
mapper input = 
  let fields = (splitOn "\t" input)
  in if (length fields) /= 19 
      then []
      else let elevation = read (fields !! 16) ::Int
               clong = (-0.00070130820003)
               clat = 0.899246234849
               lat = toRadians (read (fields !! 4)::Double)
               long = toRadians (read (fields !! 5)::Double)
               latd = clat - lat;
               longd = clong - long;
               a = ((sin (latd / 2)) ** 2) + (cos lat) * (cos clat) * ((sin (longd/2)) ** 2)
               bucket = ((2 * atan2 (sqrt a) (sqrt (1 - a))) * 6371000) / 100000
           in [(show (round bucket), [elevation])]

reducer :: (MapKey, [MapValue]) -> (ReduceKey, ReduceValue)
reducer input = (fst input, maximum (snd input))