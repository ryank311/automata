package com.king.automata.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Class to represent the service contract for a level's status.
 * @author king
 */
public final class LevelStatus implements BaseColumns {
	
	public static final String AUTHORITY = "com.king.automata.LevelStatus";
	
	public static final String SCORES_TABLE_NAME = "SCORES";
	
	/**
     * The scheme part for this provider's URI
     */
    private static final String SCHEME = "content://";
    
    /**
     * Path part for the Notes URI
     */
    private static final String PATH_LEVEL_SCORES = "/scores";
    
    /**
     * Path part for the Note ID URI
     */
    private static final String PATH_LEVEL_ID = "/scores/";
    
    /**
     * 0-relative position of a score ID segment in the path part of a note ID URI
     */
    public static final int SCORE_ID_PATH_POSITION_1 = 1;
    
    public static final int SCORE_ID_PATH_POSITION_2 = 2;
    
    /**
     * The content URI base for a single note. Callers must
     * append a numeric note id to this Uri to retrieve a note
     */
    public static final Uri CONTENT_ID_URI_BASE
        = Uri.parse(SCHEME + AUTHORITY + PATH_LEVEL_ID);
    
    /**
     * The content URI match pattern for a single note, specified by its ID. Use this to match
     * incoming URIs or to construct an Intent.
     */
    public static final Uri CONTENT_ID_URI_PATTERN
        = Uri.parse(SCHEME + AUTHORITY + PATH_LEVEL_ID + "/#" + "/#");
    
    /**
     * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
     */
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/com.king.score";

    /**
     * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
     * note.
     */
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/com.king.score";
    
    /**
     * The content:// style URL for this table
     */
    public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_LEVEL_SCORES);
    
    public static final String COLUMN_NAME_REALM = "realm";
    
    public static final String COLUMN_NAME_LEVEL = "level";
    
    public static final String COLUMN_NAME_SCORE = "score";
    
    public static final String COLUMN_NAME_ATTEMPTS = "attempts";
    
	private LevelStatus(){}
	
	
}
