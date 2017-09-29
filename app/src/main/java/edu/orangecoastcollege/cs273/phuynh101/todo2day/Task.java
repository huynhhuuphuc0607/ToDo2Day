package edu.orangecoastcollege.cs273.phuynh101.todo2day;

/**
 * Created by phuynh101 on 9/28/2017.
 */

public class Task {
    private int mId;
    private String mDescription;
    private boolean mIsDone;

    /**
     * constructor
     * @param id id - primary key
     * @param description - description
     * @param isDone - done status
     */
    public Task(int id, String description, boolean isDone) {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    /**
     * constructor
     * @param description - description
     * @param isDone - done status
     */
    public Task(String description, boolean isDone) {
        this(-1, description, isDone);
    }

    /**
     * default constructor
     */
    public Task() {
        this(-1, "", false);
    }

    /**
     * get id
     * @return id - primary key
     */
    public int getId() {
        return mId;
    }

    /**
     * get description
     * @return description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * set the description
     * @param description the description
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * get the done status
     * @return the done status
     */
    public boolean isDone() {
        return mIsDone;
    }

    /**
     * set the done status
     * @param done the done status
     */
    public void setDone(boolean done) {
        mIsDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "mId=" + mId +
                ", mDescription='" + mDescription + '\'' +
                ", mIsDone=" + mIsDone +
                '}';
    }
}
