import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Falcon9 extends Rocket {
	
	private double altitude;
	private double thrust;
	private double mass;
	private double burnTime;
	private double fuelBurn;
	private double deltaTime;
	private double netForce;
	private long forceGravity;
	private double drag;
	private double density;
	private double a;
	private double v;
	private double y;
	
	public Falcon9(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.altitude = 0;
		this.thrust = 6806000;
		this.mass = 541300;
		this.burnTime = 162;
		this.fuelBurn = 398900;
		this.deltaTime = 0;
		this.netForce = 0;
		this.forceGravity = 0;
		this.a = 0;
		this.v = 0;
		this.y = this.getY();
	}
	
	public double getAltitude() {
		return this.altitude;
	}
	public double getThrust() {
		return this.thrust;
	}
	public double getMass() {
		return this.mass;
	}
	public double getBurnTime() {
		return this.burnTime;
	}
	public double getFuelBurn() {
		return this.fuelBurn;
	}
	public double getDeltaTime() {
		return this.deltaTime;
	}
	public double getNetForce() {
		return this.netForce;
	}
	public long getForceGravity() {
		return this.forceGravity;
	}
	public double getAcceleration() {
		return this.a;
	}
	public double getVelocity() {
		return this.v;
	}
	public double getDrag() {
		return drag;
	}
	
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public void setThrust(double thrust) {
		this.thrust = thrust;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public void setBurnTime(double burnTime) {
		this.burnTime = burnTime;
	}
	public void setFuelBurn(double fuelBurn) {
		this.fuelBurn = fuelBurn;
	}
	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}
	public void setNetForce(double netForce) {
		this.netForce = netForce;
	}
	public void setForceGravity(long forceGravity) {
		this.forceGravity = forceGravity;
	}
	public void getAcceleration(double a) {
		this.a = a;
	}
	public void setVelocity(double v) {
		this.v = v;
	}
	
	public void move(double deltaTime) {
		double earthMass = 5.98*Math.pow(10, 24);
		double earthRadius = 6.37*Math.pow(10, 6);
		double gravitationalConstant = 6.67*Math.pow(10, -11);
		
		if (altitude >= 0) {
			density = 1.225;
		}
		if (altitude >= 1000) {
			density = 1.112;
		}
		if (altitude >= 2000) {
			density = 1.007;
		}
		if (altitude >= 3000) {
			density = 0.9093;
		}
		if (altitude >= 4000) {
			density = 0.8194;
		}
		if (altitude >= 5000) {
			density = 0.7364;
		}
		if (altitude >= 6000) {
			density = 0.6601;
		}
		if (altitude >= 7000) {
			density = 0.59;
		}
		if (altitude >= 8000) {
			density = 0.5258;
		}
		if (altitude >= 9000) {
			density = 0.4671;
		}
		if (altitude >= 10000) {
			density = 0.4135;
		}
		drag = 0.5*density*3.7*0.25*(Math.pow(v, 2));
		
		
		mass -= (fuelBurn/burnTime)*deltaTime;
		forceGravity = (long)(gravitationalConstant*(earthMass*mass)/Math.pow(earthRadius+altitude, 2));
		netForce = thrust - forceGravity - drag;
		a = netForce/mass;
		v += a*deltaTime;
		y += v*deltaTime;
		//System.out.println("Y: " + y);
		//System.out.println("Drag: " + drag);
		altitude += v*deltaTime;
		//System.out.println("Altitude: " + altitude);
		
		this.setDeltaTime(this.getDeltaTime() + deltaTime);
	}

}
