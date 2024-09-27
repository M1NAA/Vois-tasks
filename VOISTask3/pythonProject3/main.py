import pandas as pd

# Load the data from the CSV file
df = pd.read_csv('Employees.csv')

# 1. Remove duplicates
df = df.drop_duplicates()

# 2. Remove decimal places in the Age column
df['Age'] = df['Age'].astype(int)

# 3. Convert the USD salary to EGP (Assuming a conversion rate of 30 EGP per USD)
conversion_rate = 30
df['Salary(USD)'] = df['Salary(USD)'] * conversion_rate

# 4. Print statistics
average_age = df['Age'].mean()
median_salaries = df['Salary(USD)'].median()
male_count = df[df['Gender'] == 'M'].shape[0]
female_count = df[df['Gender'] == 'F'].shape[0]
ratio_males_to_females = male_count / female_count if female_count != 0 else 'undefined'

print(f"Average Age: {average_age}")
print(f"Median Salaries: {median_salaries}")
print(f"Ratio between Males and Females: {ratio_males_to_females}")

# 5. Write the modified DataFrame to a new CSV file
df.to_csv('modified_data.csv', index=False)
