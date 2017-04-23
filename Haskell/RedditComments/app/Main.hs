module Main where

import JobRunner
import Job
import System.Environment
import Data.Aeson
import qualified Data.ByteString.Lazy as B
import qualified Data.ByteString.Lazy.Char8 as C
import Data.Map.Strict

main :: IO ()
main = do 
        args <- getArgs
        input <- B.getContents
        if (head args) == "map"
            then B.putStr (mapperIO input)
            else B.putStr (reducerIO input)

mapperIO :: B.ByteString -> B.ByteString 
mapperIO input = encode (fromListWith (++) (flatten (Prelude.map runMapper (lines (C.unpack input)))))

reducerIO :: B.ByteString -> B.ByteString
reducerIO input = 
    let decodedInput = eitherDecode input :: Either String (Map MapKey [MapValue])
    in reducerIOMaybe decodedInput

reducerIOMaybe :: Either String (Map MapKey [MapValue]) -> B.ByteString
reducerIOMaybe a = case a of 
    Left b -> C.pack b
    Right b -> encode (fromList (Prelude.map runReducer (toList b)))


flatten :: [[a]] -> [a]
flatten [] = []
flatten (x:xs) = x ++ (flatten xs)
