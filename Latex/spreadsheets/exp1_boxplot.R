# Dades
data <- read.csv("exp1.csv", sep = ";")
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
