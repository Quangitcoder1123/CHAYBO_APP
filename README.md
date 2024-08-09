# Exercise App
## STEP COUNTER SENSOR AND STEP DETECTION SENSOR ON MOBILE PHONES

### Step Counter Sensor

The step counter sensor is used to track the total number of steps a user has taken since the last restart (power-on) of the phone. When the phone is restarted, the step counter sensor value is reset to 0. In the onSensorChanged() method, the number of steps is provided by event.values[0]; although it is a float value, the fractional part is always zero. The event timestamp represents the time of the last step. This sensor is particularly useful for applications that do not want to run in the background and need to maintain a history of steps. The sensor operates in batch mode and continuous mode. If you specify 0 or no delay in the SensorManager.registerListener() method, it operates in continuous mode; otherwise, if you specify any delay, it batches events and reports them at the specified delay. For prolonged use, the batch mode should be used as it helps save power. The step counter uses a report-on-change mode, meaning it reports events immediately when there is a change in value.

###  Step Detection Sensor

The step detection sensor triggers an event each time the user takes a step. The value reported in the onSensorChanged() method is always one, with the fractional part always zero, and the event timestamp is when the user's foot touches the ground. The step detection sensor has very low latency when reporting steps, usually within 1 to 2 seconds. It has lower accuracy and generates more false signals compared to the step counter sensor. The step counter sensor is more accurate but has higher latency when reporting steps because it uses extra time after each step to filter out any false positive values. The step detection sensor is recommended for applications that want to track steps in real-time and maintain a history of each step with their timestamps.

## BODY MASS INDEX (BMI)

BMI, or Body Mass Index, is used to determine whether a person is underweight, overweight, or has an ideal weight. This index was first proposed in 1832 by a Belgian scientist. The BMI formula is relatively simple, relying on just two metrics: height and weight:
Where height is measured in meters and weight is measured in kilograms.

BMI is not applicable to pregnant women, athletes, or those undergoing intensive physical training.

Below is the classification table of underweight and overweight based on BMI. The classification scale from the World Health Organization (WHO) is for Europeans, while the classification from the International Diabetes Federation (IDI & WPRO) is applied for Asians.
![image](https://github.com/user-attachments/assets/610b1779-47b6-416b-ae22-3551c7173709)

Table: Classification of underweight and overweight based on BMI

According to the IDI & WPRO classification for Asians, the ideal BMI for Vietnamese individuals is from 18.5 to 22.9.
![image](https://github.com/user-attachments/assets/4972e69f-8700-41fd-9777-2b5b55354dc6)

Figure: BMI index helps assess underweight and overweight

# Edamam API

The Edamam API is a platform that provides services related to food and nutrition through APIs (Application Programming Interfaces). This API offers services such as recipe search, nutrition calculation, and information retrieval about the nutritional content of various foods, among others.

Through the Edamam API, users can access and utilize food and nutrition data in their applications, including mobile and web apps. Developers can also integrate Edamam’s services into their products to offer users features related to food and nutrition.

The Edamam API provides users and developers with a powerful set of tools to leverage nutritional and food information, helping them search, analyze, and use this information in their products and applications.
![image](https://github.com/user-attachments/assets/7e567c1a-0605-401b-9b6e-ca21f2075e7d)

Figure: Edamam API Logo

## How to Use the Edamam API

To use the Edamam API, you need to sign up for an account on the Edamam website and create an API key for your application. After that, you can use Edamam’s APIs in your application through HTTP requests.

The main APIs provided by Edamam include:

Recipe Search API: Allows you to search for recipes by dish name, ingredients, and menu. To use this API, you need to send an HTTP request with search parameters such as query, diet, cuisine, meal type, and calories. The API will return search results with recipes and detailed information for each recipe.

Nutrition Analysis API: Allows you to calculate nutritional information for foods, recipes, and meals. To use this API, you need to send an HTTP request with information about the food or recipe, and the API will return the calculated nutritional components such as calories, protein, carbs, fat, and cholesterol.

Food Database API: Allows you to search for information about foods, including nutritional components and other nutritional values. To use this API, you need to send an HTTP request with the food name or food ID, and the API will return detailed information about that food.

# BUILDING THE APPLICATION

## FUNCTIONALITIES OF EACH COMPONENT

### User

View exercises.
View food.
View workout statistics.
Step counting.
## APPLICATION INTERFACE:

### User:
![image](https://github.com/user-attachments/assets/bf29c5b7-f172-4942-a00d-289656d36201)

Figure: Home page interface and step counting
![image](https://github.com/user-attachments/assets/6a754d71-e0bb-4b46-82f6-73f0e78fb33b)

Figure: Step counting statistics interface
![image](https://github.com/user-attachments/assets/2d9e6400-2619-4635-8cf4-1f5c4f256037)

Figure: Food page interface
![image](https://github.com/user-attachments/assets/176f009e-a6ab-41d9-a8cc-28f0ef9de68d)
![image](https://github.com/user-attachments/assets/0b787d07-83d2-4df4-b28a-31249d6a4c71)

Figure: Exercise page interface
![image](https://github.com/user-attachments/assets/bd2478d4-d5ca-413c-809a-5bbe45296f65)

Figure: Exercise interface
![image](https://github.com/user-attachments/assets/b18a8364-acfa-4ee9-8652-f4f263fa9cab)

Figure: Tag more interface
![image](https://github.com/user-attachments/assets/034594ea-0e52-4eb4-94fb-447e24ab538c)

Figure: User information update interface
![image](https://github.com/user-attachments/assets/16df9e04-dad9-4f6a-871e-1fa19c15c282)

Figure: Notification creation interface
![image](https://github.com/user-attachments/assets/24db3128-2cf8-45e2-a10a-83c82aa211ee)

Figure: BMI calculation page interface

