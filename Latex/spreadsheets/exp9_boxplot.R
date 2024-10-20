# Dades
data <- read.csv("exp9.csv", sep = ";")
tiempo <- data$tiempo

# Crear el boxplot amb noms
boxplot(tiempo,
        main = "Boxplot del temps d'executar l'algorisme", 
        ylab = "Preu",
        col = "lightblue")

# Mostrar la cuadrícula
grid()

# Cargar librería necesaria
library(dplyr)

# Calcular mediana, cuartiles e IQR
summary_stats <- data %>%
  summarise(
    Mediana_Move = median(tiempo, na.rm = TRUE),
    Q1_Move = quantile(tiempo, 0.25, na.rm = TRUE),
    Q3_Move = quantile(tiempo, 0.75, na.rm = TRUE),
    IQR_Move = IQR(tiempo, na.rm = TRUE),
  )

# Mostrar los resultados
print(summary_stats)