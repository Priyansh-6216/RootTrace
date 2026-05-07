import requests
import json
import random
from datetime import datetime, timedelta

API_URL = "http://localhost:8080/api/logs/upload"

services = ["auth-service", "order-service", "payment-service", "inventory-service", "api-gateway"]
levels = ["INFO", "WARN", "ERROR"]
messages = [
    "User login successful",
    "Failed to connect to database",
    "Processing order #{}",
    "Payment timeout for transaction {}",
    "Cache miss for key: user_{}",
    "Service started on port 8080",
    "Unhandled exception in request handler",
    "Rate limit exceeded for IP 192.168.1.{}",
]

def generate_logs(count=20):
    logs = []
    now = datetime.now()
    for i in range(count):
        service = random.choice(services)
        level = random.choice(levels)
        msg_template = random.choice(messages)
        message = msg_template.format(random.randint(100, 999))
        
        log = {
            "timestamp": (now - timedelta(minutes=random.randint(0, 60))).isoformat(),
            "serviceName": service,
            "logLevel": level,
            "message": message,
            "traceId": f"tr-{random.getrandbits(32):08x}",
            "spanId": f"sp-{random.getrandbits(32):08x}",
            "environment": "production"
        }
        
        if level == "ERROR":
            log["exceptionType"] = "RuntimeException"
            log["stackTrace"] = "java.lang.RuntimeException: Something went wrong\n\tat com.roottrace.service.Process.exec(Process.java:42)"
            
        logs.append(log)
    return logs

if __name__ == "__main__":
    logs = generate_logs(50)
    try:
        response = requests.post(API_URL, json=logs)
        if response.status_code == 200:
            print(f"Successfully uploaded {len(logs)} sample logs.")
        else:
            print(f"Failed to upload logs. Status code: {response.status_code}")
            print(response.text)
    except Exception as e:
        print(f"Error connecting to backend: {e}")
