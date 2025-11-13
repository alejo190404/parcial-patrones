from typing import Optional, Dict
from employee_models import Employee
from supabase import create_client, Client



class RESTEmployeeService:
    
    def __init__(self):
        self.url: str = "https://pnxqspwlzpyticceqjkj.supabase.co"
        self.key: str = "sb_secret_nKkMT7TbS0E0X2WipK73sg_7QF7bHVZ"
        self.db: Client = create_client(self.url, self.key)
    
    def fetch_employee_data(self, code: str):
        print(f"[REST Service] Fetching employee data from API for code: {code}")
        return self.db.table('Empleados').select('*').eq('code', code).execute()

