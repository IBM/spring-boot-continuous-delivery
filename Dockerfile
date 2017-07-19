FROM websphere-liberty:webProfile7
ADD jvm.options /config/
ADD target/*.war /config/dropins/
