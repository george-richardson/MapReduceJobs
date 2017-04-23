{-# LANGUAGE DeriveGeneric #-}
module Job
    ( MapKey,
      MapValue,
      ReduceKey,
      ReduceValue,
      mapper,
      reducer,
    ) where

import Data.Aeson
import Data.List
import GHC.Generics
import Data.List.Split
import Data.Char
import qualified Data.ByteString.Lazy.Char8 as C

type MapKey = String
type MapValue = Int
type ReduceKey = String
type ReduceValue = Double

catWords = ["cat", "kitten", "kittycat", "kitty", "meow", "purr"]
dogWords = ["dog", "doggy", "puppy", "doggo", "pupper", "woof"]

data Comment = Comment {subreddit :: String, body :: String} deriving (Generic, Show)
instance FromJSON Comment

mapper :: String -> [(MapKey, [MapValue])]
mapper input = case eitherDecode (C.pack input) :: Either String Comment of 
                 Left c -> []
                 Right c -> [(subreddit c, [sum (map scoreWord (wordsBy (not . isLetter) (lowerString (body c))))])]
reducer :: (MapKey, [MapValue]) -> (ReduceKey, ReduceValue)
reducer input = (fst input, average (snd input))

scoreWord :: String -> Int
scoreWord word = if elem word catWords
                   then 1
                   else if elem word dogWords
                    then -1
                    else 0

average :: [Int] -> Double
average xs = fromIntegral (sum xs) / fromIntegral (genericLength xs)

lowerString str = [ toLower loweredString | loweredString <- str]