package dk.unf.sdc.gruppec;

import android.os.CountDownTimer;

//countdowntimer is an abstract class, so extend it and fill in methods
public class Counter extends CountDownTimer{
	
	boolean isDone = false;
	



public Counter(long millisInFuture, long countDownInterval) {
super(millisInFuture, countDownInterval);}


@Override
public void onFinish() {
	isDone = true;
	
}

@Override
public void onTick(long millisUntilFinished) {
	// TODO Auto-generated method stub
	
}
	public boolean isDone() {
		return isDone;
	}


	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

}