FROM websphere-liberty:webProfile7
ADD target/*.war /config/dropins/
