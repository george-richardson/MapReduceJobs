module Job
    ( MapKey,
      MapValue,
      ReduceKey,
      ReduceValue,
      mapper,
      reducer,
    ) where
import Data.List.Split
import Data.Maybe
import Text.Read

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
      else let elevation = readMaybe (fields !! 16) ::Maybe Int
               lat = readMaybe (fields !! 4)::Maybe Double
               long = readMaybe (fields !! 5)::Maybe Double
           in unwrapMaybe elevation lat long

reducer :: (MapKey, [MapValue]) -> (ReduceKey, ReduceValue)
reducer input = (fst input, maximum (snd input))

unwrapMaybe :: Maybe Int -> Maybe Double -> Maybe Double -> [(MapKey, [MapValue])]
unwrapMaybe (Just elevation) (Just lat) (Just long) = let latr = toRadians lat
                                                          longr = toRadians long
                                                          clong = (-0.00070130820003)
                                                          clat = 0.899246234849
                                                          latd = clat - latr;
                                                          longd = clong - longr;
                                                          a = ((sin (latd / 2)) ** 2) + (cos latr) * (cos clat) * ((sin (longd/2)) ** 2)
                                                          bucket = ((2 * atan2 (sqrt a) (sqrt (1 - a))) * 6371000) / 100000
                                                      in [(show (round bucket), [elevation])]
unwrapMaybe _ _ _ = []