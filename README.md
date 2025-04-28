# oopjava_mlproject

A machine learning classifier based on the Naive Bayes Classifier.
Theme: PropertyIsRented
Student Number: C23767071
Video:  --wip--


So the aim for the assignment was to create a machine learning classifier as it says above. First thing was to generate a dataset.
We were told to use gen-AI so that's what I did, which is how my PropertyDataGenerator class came to be. ChatGPT kinda struggled to generate the
data for me, or I struggled to understand what it was doing so this class was used to find all possible permutations/combinations, and to populate the dataset.
I then made a function for generating/assigning the labels to the random data, based on which properties I believed would be more desirable and therefore rented.
(These conditions were: grassy garden, double bed, and fixed lease).


The FileProcessor class was carried over from one of the labs and it was used to read the csv file we just generated, as well as populate a list of string arrays
which was used for the creation of my Map or Frequency/Likelihood table. The FileProcessor class also was used for writing to the file for when users wanted to
add new rows to the csv. The readFile function was called upon many times by the GUI and NaiveBayesClassifier class (henceforth NBC) in order to update
and retrain the HashMaps/Frequency/Likelihood table. This was done to ensure that newly added rows were actually relevant and utilised by the classifier
and not just sitting in the CSV for fun. I thought about adding a function to clear the dataset or restore it to it's default state but opted against it :P. 
-- Now I am currently in the process of updating the readFile functionality by including some method overloading for the level 4 functionality but I have yet
to implement it so cannot talk about it. Hopefully this section is removed and updated if and when I get level 4 done. --


The NBC class is where the maths and hashmap navigation mostly occur. It is filled with a bunch of comments I added to try explain what I was doing but in summary
it stores that returned list of string arrays in tableRows, which is used whenever generating/updating/retraining the Map/Freq/Like table. The predict() function
is the dynamic implementation of the Bayes theorem. The function finds the priorYes and priorNo by fetching the value of their counts stored on the Map,
multiplies together the probability of the independent events(each feature) , seperated by label, to give the posteriorProbability or final probability for Yes and No,
based on the entered in values. Evaluating which of the two is higher to give a prediction on what label the inputted combination will possess.


The GUI class is as it sounds, it is the screen and it is where I add all the interactive buttons for using the classifier. I'm not really a fan of how the code
turned out here it feels pretty sloppy but I don't really know how to improve it. It implements the ActionListener interface to handle button interaction.
There is an instance of the FileProcessor and the NBC class here as I sometimes need to call on functions from those classes on button press.
I'm not sure why but it felt wrong to be instatiating those classes here, but I couldn't think of a better implementation. Not sure if this is a mental block,
as in I think it's wrong when it isn't, or if it is actually just not good lol.


That's about it, of course the control class starts everything up. I hope I demonstrated my understanding of OOP concepts well, with the separte classes, encapsulation, polymorphism
and whatnot. 
Thank you,
Andrew
