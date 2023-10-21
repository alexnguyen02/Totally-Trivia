Team Name: Trivia Cash


Domain: Our domain will be a trivia game. Players will either create an account or login with their current account (if they have one) and they will answer questions in order to earn in-game cash, which they’ll use to buy different aesthetic designs for the game. There’ll be single-player and multiplayer modes.


Software Specification:

We are planning to develop an application that contains both single-player and multiplayer game modes. However, before accessing these game modes, a new user must first create an account by selecting a unique username and password, and a returning user must log in using their pre-existing info. Aside from Create an Account, there will also be Log-In, Log-Out, and Delete Your Account options. If the user selects the single player mode, they will be put up against a series of multiple-choice questions from a range of different categories and levels of difficulty (Easy, Medium, Hard). These questions will be generated using the Trivia API. In this gamemode, each right answer will win the player in-game cash, with extra ‘bonus’ cash attributed for long streaks of correct answers, and for answering questions of a higher difficulty. This in-game cash can then be used to unlock certain perks in the game, such as a new skin or profile badge. Meanwhile, in the multiplayer game-mode, the player will be able to go up against different players in a live trivia contest. For​ this fe​​ature, we will implement an algorithm that matches two players who are in the multiplayer game mode to compete against each other. The players will have​​ to get as many correct answers as they can from the same set of questions under the same time constraint. Whoever gets more correct answers wins the game, or whoever finishes all the answers earlier wins the game in case both players have the same number of correct answers. Additionally, we are looking to implement a leaderboard system which ranks different players based on their number of multiplayer wins.


User Stories:

Daniel recently found out about the Trivia Cash app from his friends, and wants to get in on the fun. He tries to create an account, but he is notified that his username is taken. After selecting a unique username and trying again, he successfully creates a new account. [Daniel’s story]
River has an account, and he put username and password of his account and click “log in”, then he logs in to the system [River’s story]
River has an account, and after he logs in to the system, he click the “log out ” button to log out. [River’s story]
In a singleplayer game, Kate selects the History category, sets the question difficulty to Hard, and selects a time limit of 3 minutes. She then plays a game. [Kate’s story]
River has an account and has played Trivia Cash for a while, so he has 100 dollars of in-game cash. He then buys a new colour scheme from the store. [River’s story]
Alex is one of the top players on the leaderboard in Trivia Cash. They have always wanted to play against another equally challenged nemesis. After logging in, Alex selects Multiplayer mode and gets matched up with Kate for a one-on-one trivia game. [Alex’s story] 
After losing to Kate in a one-on-one trivia game, Alex becomes enraged and decides to delete their Trivia Cash account. [Alex’s story] 
A group of university students are debating on who has the better general knowledge. In order to settle their debate, they decide that whoever is placed higher on the global leaderboard in Trivia Cash at the end of the week will win the competition. After many multiplayer head-to-head games between the students, John has the most wins, and finishes at the top of the leaderboard. [Team’s story]
