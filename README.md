# oopjava_mlproject

A machine learning classifier based on the Naive Bayes Classifier.

Theme: PropertyIsRented

Student Number: C23767071

Video: [link](https://vimeo.com/1079841158/4fb41920a2?share=copy)

Frequency Table: available in the repo as freqTable.xlsx

Classes:

Control - Starts up the code

FileProcessor - Read my csv file

GUI - Class inherited from JFrame implementing ActionListener interface. The Interactive GUI of the application

NaiveBayesClassifier - Class that handles the main functionality of dynamic calculation of Bayes Theorem.

PropertyDataGenerator - Class used to generate and populate my dataset

StratifiedSplit - Class used to split data and stratify evenly between train and test groups for level 4 functionality.


What would I add if I had more time:


Make the GUI prettier.

Clean up some of the classes for sure. I think I could have extracted my data from the rows and rather than always output my rows as a string array, instead store it in another class
of data like I had thought to implement early on in the code. I don't know why I retconned that, maybe it was just starting to get too complicated in my head.

Probably could've made the NBC implement a Classifier interface but I thought it was an unnecessary demonstration of understanding.

I would also improve the calculation of my NBC's accuracy by including the calculation of a F1 score, maybe even displaying a confusion matrix to the user.

Below is the readme I had written before I realsied there were specific requirements for the readme.

Original Readme section
------------------------------------------------------



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
based on the entered in values. Evaluating which of the two is higher to give a prediction on what label the inputted combination will possess. I decided to keep that little '--'
section in the above paragraph to show how I changed my functionality. As of writing my level 4 is done and you are able to test the data but I am unsatisfied with my classifier accuracy.
I believe this is because I have not yet tried to stratify my data evenly, but I will be trying that next! Anyway the method overloading was implemented here my likelihood table functions
were now being passed two integer arguments, for where to start and finish training the dataset. I then called upon my predict function and compared the output to the actual label and
made note of the amount correct to give my final accuracy.


The GUI class is as it sounds, it is the screen and it is where I add all the interactive buttons for using the classifier. I'm not really a fan of how the code
turned out here it feels pretty sloppy but I don't really know how to improve it. It implements the ActionListener interface to handle button interaction.
There is an instance of the FileProcessor and the NBC class here as I sometimes need to call on functions from those classes on button press.
I'm not sure why but it felt wrong to be instatiating those classes here, but I couldn't think of a better implementation. Not sure if this is a mental block,
as in I think it's wrong when it isn't, or if it is actually just not good lol.


That's about it, of course the control class starts everything up. I hope I demonstrated my understanding of OOP concepts well, with the separte classes, encapsulation, polymorphism
and whatnot. 
Thank you,
Andrew
