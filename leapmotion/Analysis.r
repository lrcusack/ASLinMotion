i = 8;

output <- read.csv("~/Code/hadoopopotomus/leapmotion/output.csv", header=FALSE)


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

means = vector(mode = "numeric", length = length(names(output)));
results = subset(output,subset=(FALSE))
letters = levels(output$letter)

for (i in 1:26){
  temp = subset(output,subset = (as.numeric(output$V1)==i))
  for (j in 2: length(names(output))){
      var = paste("V",as.character(i),sep="")
      means[j] = mean(temp[[var]])
  }
}
