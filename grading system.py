# I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
# Any code taken from other sources is referenced within my code solution
# naveed mohamed nisfer
# w1899125
# 24/11/2022

print("welcome to westminster grading system!")

pass_credit = 0
defer_credit = 0
fail_credit = 0

progress = []
trailer = []
retriever = []
exclude = []

total_outcomes = 0

# line17-36 https://github.com/vizchamz/Predict-progression-outcomes/blob/main/part3.py 
def histogram_print():
    # prints the histogram line by line
    print("\n") 
    print("-" * 60)
    # prints out horizontal histogram on a seperate line
    print("Horizontal Histogram\n")
    # prints out progress and cheks the how many times progress has been achived and prints out the amount of stars needed. same thing for trailer, retriever and exclude.
    print("Progress", len(progress), " :", "*" * len(progress))
    print("Trailer", len(trailer), "  :", "*" * len(trailer))
    print("Retriever", len(retriever), ":", "*" * len(retriever))
    print("Excluded", len(exclude), " :", "*" * len(exclude))
    # total outcome adds up all the outcomes that the user has done and prints it out.
    total_outcomes = len(progress) + len(trailer) + len(retriever) + len(exclude)
    print("\n")
    # prints it out on a sepreate line
    print(total_outcomes, "outcomes in total.")
    print("-" * 60)


close = ""

def user_id():
    while True:
        u_id = input('enter your student id:')
        # checks if the first character is a lower case
        u_id [0].lower()
        # makes sure that the length of the student id is 8 and not less or more
        if(len(u_id)<8) or (len(u_id)>8):
            print('ID is not correct length. please try again')
            # makes sure that it starts with w
        elif (u_id[0] != 'w'):
            print ('ID must start with w')
        else:
            return u_id

# line 7-26 https://stackoverflow.com/questions/58791012/how-do-i-implement-a-range-function-in-this-program 
def input_valid_number(which="pass"):
    while True:
        #  here is a loop that asks the user to enter there pass, defer and fail credits. 
        credit = input("Enter your {} credits: ".format(which)).strip() 
        # checks if the value enterd is an integer using boolean
        if credit.isdigit():
            credit = int(credit)
            # divides the number by 20 to make sure that the remainder is zero so it can accept the value entered otherwise a message will come saying incorrect.
            if 0 <= credit <= 120 and (credit % 20) == 0:
                return credit
            else:
                print("out of range")
        else:
            print("Not an Integer Value.")

            
while True:
    while True:
        student_id = user_id();
        pass_credit = input_valid_number("pass")
        defer_credit = input_valid_number("defer")
        fail_credit = input_valid_number("fail")
        # checks if the numbers entered for each variable adds up to 120 credits, if not a message will appear saying total incorrect.
        if sum([pass_credit, defer_credit, fail_credit]) == 120:
            break
        print("total not add up to 120 credits")


    
#  here it checks whether the numbers entered correlate with the grading infromation it has stored. Once it recievs all the inputs and total is 120 it outputs the correct progression outcome.
    if pass_credit == 120: 
            print("progress") 
            progress.append(0)
            # if the value for fail credit is greater or equal to 80 it compares it to the other progression outocmes and checks if it matches the data stored to decide an outcome.
    elif fail_credit >= 80:
           print("exclude")
           exclude.append(0)
    elif pass_credit == 100:
            print("progress (module trailer)")
            trailer.append(0)
    elif defer_credit <=120:
             print("do not progress - module retriever")
             retriever.append(0)
            

    

# after a progrssion outcome has been given, the user has an option to press y to continue entering credits to check outcomes or press q to quit the program.
    # print(student_id, pass_credit, defer_credit, fail_credit)
    print("\n")
    inputs = input("please enter q to quit or y to continue:")
    if inputs == "y":
        # if y is pressed the program will loop back to the beginning to start from the beginning.
        continue
    else:
        print(student_id)
        histogram_print()
        print("end")
        # otherwise break will stop and end the program
        break

        
        

