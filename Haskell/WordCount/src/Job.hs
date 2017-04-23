module Job
    ( MapKey,
      MapValue,
      ReduceKey,
      ReduceValue,
      mapper,
      reducer,
    ) where

type MapKey = String
type MapValue = Int
type ReduceKey = String
type ReduceValue = Int

mapper :: String -> [(MapKey, [MapValue])]
mapper input = map (\a -> (a, [1])) (words input)
reducer :: (MapKey, [MapValue]) -> (ReduceKey, ReduceValue)
reducer input = (fst input, sum (snd input))
