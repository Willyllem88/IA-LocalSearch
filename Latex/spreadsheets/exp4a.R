# Dades
data <- read.csv("exp4a.csv", sep = ";")
move <- data$mv
swap <- data$sw
mvsw <- data$mvsw

# Crear el boxplot amb noms
boxplot(move, swap, mvsw,
        main = "Boxplot del preu resultant de diferents operator sets",
        names = c("Move", "Swap", "Move + Swap"),
        ylab = "Preu",
        col = "lightblue")

# Mostrar la cuadrícula
grid()

# Cargar librería necesaria
library(dplyr)

# Calcular mediana, cuartiles e IQR
summary_stats <- data %>%
  summarise(
    Mediana_Move = median(mv, na.rm = TRUE),
    Q1_Move = quantile(mv, 0.25, na.rm = TRUE),
    Q3_Move = quantile(mv, 0.75, na.rm = TRUE),
    IQR_Move = IQR(mv, na.rm = TRUE),

    Mediana_Swap = median(sw, na.rm = TRUE),
    Q1_Swap = quantile(sw, 0.25, na.rm = TRUE),
    Q3_Swap = quantile(sw, 0.75, na.rm = TRUE),
    IQR_Swap = IQR(sw, na.rm = TRUE),

    Mediana_Mvsw = median(mvsw, na.rm = TRUE),
    Q1_Mvsw = quantile(mvsw, 0.25, na.rm = TRUE),
    Q3_Mvsw = quantile(mvsw, 0.75, na.rm = TRUE),
    IQR_Mvsw = IQR(mvsw, na.rm = TRUE)
  )

# Mostrar los resultados
print(summary_stats)