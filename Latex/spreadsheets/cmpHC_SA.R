# Cargar datos desde cmpHC_SA.csv
data <- read.csv("cmpHC_SA.csv", sep=",", dec=".")

# Crear boxplots
boxplot(data$`PREU_HC`, data$`TPREU_SA`,
        names = c("Hill Climbing", "Simulated Annealing"),
        main = "Comparació de preus resultants entre Hill Climbing y Simulated Annealing",
        ylab = "Preu (€)",
        col = c("lightblue", "lightblue"))
grid()



boxplot(data$`TEMPS_HC`, data$`TEMPS_SA`,
        names = c("Hill Climbing", "Simulated Annealing"),
        main = "Comparació de temps entre Hill Climbing y Simulated Annealing",
        ylab = "Tiempo (ms)",
        col = c("lightblue", "lightblue"))
grid()