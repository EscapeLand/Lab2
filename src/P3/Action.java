public interface Action {
	/**
		place a piece on the board.
		@param Piece a, the piece to place.
		@param Positon to, where to place the piece.
		@return false if the piece is on the board, or the position is taken by another piece, or the position is out
				of the board. else true.
	*/
	abstract public boolean addPiece(Piece a, Position to);
	
	/**
		move the piece on from to to
	    @param Position from, from.
	    @param Position to, to.
	    @return false if from or to is not on board; or Position to is taken by another Piece; or Position from has
	            no piece; or from is equal to to. else true.
	*/
	abstract public boolean movePiece(Position from, Position to);
	
	/**
		remove a piece from the board.
	    @param Position, where, the position to remove a piece.
	    @return false if where is not on board; or no piece on where. else true.
	 */
	abstract public boolean removePiece(Position where);
	
	/**
		remove the piece on to, and move the piece on to to from.
	    @param Position from, from.
	    @param Position to, to.
	    @return false if from or to is not on board; or to is equal to from; or no piece on to and from.
	            else true.
	 */
	abstract public boolean replacePiece(Position from, Position to);
}