# Càrrega de les biblioteques necessàries
library(ggplot2)

# dades
data <- data.frame(
  Felicitat_0 = c(1101.2, 1049.87, 998.89, 1086.55, 890.16, 985.16, 879.32, 1072.78, 1041.45, 1073.22),
  Felicitat_5 = c(1172.4, 1121.47, 1031.62, 1127.04, 979.98, 1038.41, 949.95, 1112.85, 1128.44, 1140.76),
  Felicitat_10 = c(1230.4, 1158.61, 1051.68, 1194.66, 985.98, 1044.28, 975.77, 1166.94, 1215.91, 1169.41),
  Felicitat_15 = c(1188.77, 1173.77, 1044.28, 1214.93, 988.38, 1071.22, 993.66, 1180.88, 1211.57, 1178.55),
  Felicitat_20 = c(1190.59, 1166.17, 1037.86, 1197.49, 994.91, 1034.4, 1001.99, 1157.97, 1219.01, 1186.62)
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
