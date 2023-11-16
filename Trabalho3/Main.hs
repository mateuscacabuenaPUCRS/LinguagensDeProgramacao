-- Trabalho 3 de Linguagens de Programação
-- Carolina Ferreira, Mateus Caçabuena e Nicholas Spolti
-- https://replit.com/@mateuscacabuena/Trabalho-3-Linguagens-de-Programacao#Main.hs

import Data.List (intercalate)

data Item = Item { nome:: String, preço:: Int } deriving Show

constructionList :: [Item]
constructionList = [Item "Cimento" 50, Item "Tijolo" 10, Item "Madeira" 100]

main :: IO ()
main = do
  putStrLn "Bem-vindo à sua lista de construção de um prédio!"
  menu constructionList

menu :: [Item] -> IO ()
menu constructionList = do
  putStrLn "Selecione a opção que deseja realizar: "
  putStrLn "[0] Sair da aplicação"
  putStrLn "[1] Adicionar item na lista"
  putStrLn "[2] Listar todos os itens"
  
  putStrLn ""
  putStrLn "Opção escolhida: "
  option <- getLine
  putStrLn ""
  
  case option of
    "0" -> putStrLn "Obrigado por utilizar nosso sistema, programa finalizado."
    "1" -> do
      putStrLn "Digite o nome do item que deseja adicionar:"
      itemNome <- getLine

      putStrLn "Digite o preço do item:"
      priceStr <- getLine
      putStrLn ""
      
      let price = read priceStr :: Int
      let newItem = Item itemNome price
      let updatedList = newItem : constructionList

      putStrLn $ "Item '" ++ itemNome ++ "' adicionado à lista com preço de R$" ++ show price
      menu updatedList
    "2" -> do
      putStrLn "Itens na lista de construção:"
      let itemStrings = [nome item ++ " R$" ++ show (preço item) | item <- constructionList]
      putStrLn $ intercalate ", " itemStrings
      putStrLn ""
      menu constructionList
    _ -> do
      putStrLn "Opção inválida. Tente novamente."
      menu constructionList
