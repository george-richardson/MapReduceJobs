module JobRunner
    ( runMapper,
     runReducer
    ) where
import Job

runMapper :: String -> [(MapKey, [MapValue])]
runMapper inp = mapper inp

runReducer :: (MapKey, [MapValue]) -> (ReduceKey, ReduceValue)
runReducer inp = reducer inp

