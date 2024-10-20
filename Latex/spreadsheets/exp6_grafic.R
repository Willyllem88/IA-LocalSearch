# Càrrega de les biblioteques necessàries
library(ggplot2)

# dades
data <- data.frame(
  Felicitat_0 = c(1077, 1013, 958, 1038, 860, 968, 853, 1058, 1008, 1039),
  Felicitat_5 = c(1205, 1165, 1055, 1180, 1040.85, 1062, 1012, 1166.63, 1147, 1135.21),
  Felicitat_10 = c(1312, 1244, 1069, 1283, 1054, 1101, 1075, 1242, 1296, 1244),
  Felicitat_15 = c(1308, 1244, 1069, 1282.84, 1054, 1100, 1075, 1255, 1283.91, 1243),
  Felicitat_20 = c(1308, 1244, 1069, 1283, 1054, 1100, 1075, 1255, 1283.91, 1243)
)

# Crear un dataframe llarg per a graficar
data_long <- data.frame(
  Felicitat = c(0, 5, 10, 15, 20),
  Preu = colMeans(data)
)

# Crear el gràfic amb totes les dades
ggplot(data_long, aes(x = Felicitat, y = Preu)) +
  geom_line(color = "blue") +
  geom_point(size = 3, color = "black") +
  labs(title = "Evolucio del Preu a mesura que augmenta la Felicitat",
       x = "Ponderacio de la Felicitat",
       y = "Preu") +
  scale_y_continuous(limits = c(0, NA)) +  # Ajustar el límit inferior de l'eix Y a 0
  theme_minimal()
