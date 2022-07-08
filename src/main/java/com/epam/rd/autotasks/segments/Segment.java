package com.epam.rd.autotasks.segments;


class Segment {
    private final Point start;
    private final Point end;
    public Segment(Point start, Point end) {
    if (start.getX()==end.getX()&&start.getY()==end.getY())
        throw new IllegalArgumentException();
    this.start=start;
    this.end=end;
    }

    double length() {
        double deltaX=start.getX()-end.getX();
        double deltaY=start.getY()-end.getY();
        return Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
    }

    Point middle() {
        double midX=(start.getX()+end.getX())/2;
        double midY=(start.getY()+end.getY())/2;
        return new Point(midX,midY);
    }
    private double findA(){
        return (start.getY()-end.getY())/(start.getX()-end.getX());
    }

    Point intersection(Segment a) {
        if (findA()==a.findA())
            return null;

        double x1x3= this.start.getX()-a.start.getX();      // x1-x3
        double y3y4= a.start.getY()-a.end.getY();           // y3-y4
        double y1y3= this.start.getY()-a.start.getY();      // y1-y3
        double x3x4= a.start.getX()-a.end.getX();           // x3-x4
        double x1x2= this.start.getX()-this.end.getX();     // x1-x2
        double y1y2= this.start.getY()-this.end.getY();     // y1-y2

        double t=(x1x3*y3y4-y1y3*x3x4)/(x1x2*y3y4-y1y2*x3x4);
        double u=(x1x3*y1y2-y1y3*x1x2)/(x1x2*y3y4-y1y2*x3x4);

        if (t<0 || t>1 || u<0 || u>1)
            return null;

        double xPoint=this.start.getX()+t*(-x1x2);
        double yPoint=this.start.getY()+t*(-y1y2);
        return new Point(xPoint,yPoint);
    }

}
