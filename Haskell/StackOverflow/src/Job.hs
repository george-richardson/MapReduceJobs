{-# LANGUAGE Arrows, NoMonomorphismRestriction #-}
module Job
    ( MapKey,
      MapValue,
      ReduceKey,
      ReduceValue,
      mapper,
      reducer,
    ) where

import Text.XML.HXT.Core
import Data.Time

type MapKey = String
type MapValue = Int
type ReduceKey = String
type ReduceValue = Int

data User = User 
  { reputation :: Int, creationDate :: String }
  deriving (Show, Eq)

mapper :: String -> [(MapKey, [MapValue])]
mapper input = let user = head (runLA (xread >>> getUser) input)
                   today = (parseTimeOrError True defaultTimeLocale "%Y-%m-%d" "2017-04-01")
                   diff = diffDays today (parseTimeOrError True defaultTimeLocale "%Y-%m-%dT%H:%M:%S%Q" (creationDate user))
                   repPerDay = fromIntegral (reputation user) / fromIntegral diff
               in if repPerDay > (1::Double) 
                    then [("active", [1])]
                    else [("inactive", [1])]

reducer :: (MapKey, [MapValue]) -> (ReduceKey, ReduceValue)
reducer input = (fst input, sum (snd input))

atTag tag = deep (isElem >>> hasName tag)

getUser = atTag "row" >>>
  proc l -> do
    reputation <- getAttrValue "Reputation"   -< l
    created    <- getAttrValue "CreationDate" -< l
    returnA -< User (read reputation) created