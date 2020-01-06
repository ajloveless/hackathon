# KSU Hackathon 2019 - Equifax's Problem

## Hackathon description

>The College of Computing and Software Engineering (CCSE) Hackathon is our college's premier event in showcasing student talent and connecting companies to our students. Through dedicated "threads" (aka themes) within the event, companies can raise awareness of opportunities for students to work with them, increase brand awareness, and locate top talent for computing workforce. Students can hone their skills, apply the knowledge they gain inside the classroom in authentic, real-world problems, and connect with internship and job opportunities.
>
>Come together with friends and other students, meet new people, strengthen your design and development skills, and connect with companies eager to identify talent and see what you can make… all within one weekend. This hackathon will strengthen your resume, let you apply what you’re doing in the classroom into real-world scenarios, and let you earn valuable cash, prizes, and awards (over $15k in prize money available)!
>
>The CCSE Hackathon is open to all undergraduate and graduate CCSE (ACS, BASIT, IT, CS, SWE, CGDD and PhD in Data Science and Analytics).
>
>Each industry sponsor will present challenges related to their thread of industry. Multiple challenges are possible for each sponsor. In each of these challenges, students will be chosen to form teams of 3 (and strictly no more than 3) to solve these challenges presenting the best of our students in Computer Science, Information Technology, Software Engineering and Game Development. Many different skillsets on every team are desired for the best results for our industry leaders.

\- Kennesaw CSSE 

## Equifax's challenge
[challenge.pdf](https://github.com/ajloveless/hackathon/blob/master/challenge.pdf)

## Credits

Nelson Bonilla - Presentation

Andrew Gavieres - Statistics/Graphs

Andrew Loveless - Code, Project Lead


## Our solution
We implemented both a bruteforce and a dictionary attack to show the performance difference between them. The generation of the locks was done through hundreds of iterations of random samples, with the best being selected for each size created. Through more samples (10,000+) our results were more accurate, however we were still able to generate rough estimates quicker with less samples. Configuration of that variable can be found [here](https://github.com/ajloveless/hackathon/blob/master/comboMaker.java#L22). Gavieres' statistics are not included in this repository.

## Files

FilterLength - Creates a new dictionary based on length given, implements advanced parsing of ranges

Cracker - Shows differences between bruteforce and dictionary attacks

comboMaker - Creates lock configurations of a multiple sizes
