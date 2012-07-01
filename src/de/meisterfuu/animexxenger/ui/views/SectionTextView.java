/*
    This Software(Animexxenger) is based on BEEM:\n\nBEEM is a videoconference application on the Android Platform.

    Copyright (C) 2009-2011 by Frederic-Charles Barthelery,
                               Nikita Kozlov,
                               Vincent Veronis.

    This file is part of BEEM.

    BEEM is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    BEEM is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with BEEM.  If not, see <http://www.gnu.org/licenses/>.

    Please send bug reports with examples or suggestions to
    contact@beem-project.com or http://www.beem-project.com/

*/
package de.meisterfuu.animexxenger.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import de.meisterfuu.animexxenger.R;

/**
 * This class implements a custom TextView with an underscore section line.
 * It can be used to make tabs.
 *
 */
public class SectionTextView extends TextView {
    private static final int DEFAULT_PRINCIPAL_COLOR = 0xffffffff;
    private static final int DEFAULT_PRINCIPAL_LINE_SIZE = 8;
    private static final int DEFAULT_NON_PRINCIPAL_COLOR = 0xff555555;
    private static final int DEFAULT_NON_PRINCIPAL_LINE_SIZE = 3;
    private static final int SECTION_SPACE_SIZE = 5;
    private boolean principal;
    private int principalColor;
    private int nonPrincipalColor;
    private float principalLineSize;
    private float nonPrincipalLineSize;
    private float density;

    private Paint sectionPaint;


    /**
     * Create a SectionTextView.
     *
     * @param context the android context
     * @param attrs the android attributes
     */
    public SectionTextView(final Context context, final AttributeSet attrs) {
	super(context, attrs);
	initSectionTextView();

	TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SectionTextView);

	principalColor = a.getColor(R.styleable.SectionTextView_principalColor, DEFAULT_PRINCIPAL_COLOR);
	principalLineSize = a.getDimension(R.styleable.SectionTextView_principalLineSize,
		DEFAULT_PRINCIPAL_LINE_SIZE * density);
	nonPrincipalColor = a.getColor(R.styleable.SectionTextView_nonPrincipalColor, DEFAULT_NON_PRINCIPAL_COLOR);
	nonPrincipalLineSize = a.getDimension(R.styleable.SectionTextView_nonPrincipalLineSize,
		DEFAULT_NON_PRINCIPAL_LINE_SIZE * density);

	a.recycle();
    }

    /**
     * Create a SectionTextView.
     *
     * @param context the android context
     */
    public SectionTextView(final Context context) {
	super(context);
	initSectionTextView();
    }

    /**
     * Set the textview in principal mode.
     * It will show up with a distinctive color.
     *
     * @param principal the mode
     */
    public void setPrincipal(boolean principal) {
	this.principal = principal;
    }

    /**
     * Get the principal mode of the view.
     *
     * @return the mode
     */
    public boolean isPrincipal() {
	return principal;
    }


    /**
     * Get the color used to show the view in principal mode.
     *
     * @return the color
     */
    public int getPrincipalColor() {
	return principalColor;
    }

    /**
     * Set the color used to show the view in principal mode.
     *
     * @param principalColor the color
     */
    public void setPrincipalColor(int principalColor) {
	this.principalColor = principalColor;
    }

    /**
     * Get the color used to show the view when not in principal mode.
     *
     * @return the color
     */
    public int getNonPrincipalColor() {
	return nonPrincipalColor;
    }

    /**
     * Set the color used to show the view when not in principal mode.
     *
     * @param nonPrincipalColor the  color
     */
    public void setNonPrincipalColor(int nonPrincipalColor) {
	this.nonPrincipalColor = nonPrincipalColor;
    }

    /**
     * Get the size of the line section in principal mode.
     *
     * @return the size of the line
     */
    public float getPrincipalLineSize() {
	return principalLineSize;
    }

    /**
     * Set the size of the line section in principal mode.
     *
     * @param principalLineSize the size of the line
     */
    public void setPrincipalLineSize(float principalLineSize) {
	this.principalLineSize = principalLineSize;
    }

    /**
     * Get the size of the line section when not in principal mode.
     *
     * @return the size of the line
     */
    public float getNonPrincipalLineSize() {
	return nonPrincipalLineSize;
    }

    /**
     * Set the size of the line section when not in principal mode.
     *
     * @param nonPrincipalLineSize the size of the line
     */
    public void setNonPrincipalLineSize(float nonPrincipalLineSize) {
	this.nonPrincipalLineSize = nonPrincipalLineSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	int height = getMeasuredHeight();
	height += Math.max(principalLineSize, nonPrincipalLineSize)
	    + SECTION_SPACE_SIZE * density; // line width + space
	setMeasuredDimension(getMeasuredWidth(), height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);
	if (isPrincipal()) {
	    sectionPaint.setColor(principalColor);
	    sectionPaint.setStrokeWidth(principalLineSize);
	    canvas.drawLine(0, getHeight() - principalLineSize / 2,
		    getWidth(), getHeight() - principalLineSize / 2, sectionPaint);
	} else {
	    sectionPaint.setColor(nonPrincipalColor);
	    sectionPaint.setStrokeWidth(nonPrincipalLineSize);
	    canvas.drawLine(0, getHeight() - nonPrincipalLineSize / 2, getWidth(),
		    getHeight() - nonPrincipalLineSize / 2, sectionPaint);
	}
    }

    /**
     * Initialize the SectionTextView with the default value.
     */
    private void initSectionTextView() {
	density = getResources().getDisplayMetrics().density;
	sectionPaint = new Paint();
	principalColor = DEFAULT_PRINCIPAL_COLOR;
	principalLineSize = DEFAULT_PRINCIPAL_LINE_SIZE * density;
	nonPrincipalColor = DEFAULT_NON_PRINCIPAL_COLOR;
	nonPrincipalLineSize = DEFAULT_NON_PRINCIPAL_LINE_SIZE * density;
    }

}
