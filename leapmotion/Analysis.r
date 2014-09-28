i = 8;

output <- read.csv("~/Code/hadoopopotomus/leapmotion/output.csv", header=FALSE)

mynames = names(output)
mynames[1] = "letter"
mynames[2] = "palmX"
mynames[3] = "palmY"
mynames[4] = "palmZ"

names(output) = mynames

while (i <65){
  
  if((i-1) %% 3 == 0){
    i1 = paste("V",as.character(i),sep="")
    i=i+1
    i2 = paste("V",as.character(i),sep="")
    i=i+1
    i3 = paste("V",as.character(i),sep="")
    
    output[i1] = output[i1]-output$V5
    output[i2] = output[i2]-output$V6
    output[i3] = output[i3]-output$V7
  }
  i=i+1;
}

results = list()
letters = factors(output$letter)
for (i in 1:26){
  temp = subset(output,subset = (as.numeric(output$letter)==i))
  results[[i]] = summary(temp)
}
