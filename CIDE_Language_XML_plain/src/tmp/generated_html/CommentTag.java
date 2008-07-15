package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CommentTag extends GenASTNode {
  public CommentTag(ArrayList<CommentContent> commentContent, CommentEnd commentEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<CommentContent>("commentContent", commentContent),
      new PropertyOne<CommentEnd>("commentEnd", commentEnd)
    }, firstToken, lastToken);
  }
  public CommentTag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CommentTag(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<CommentContent> getCommentContent() {
    return ((PropertyZeroOrMore<CommentContent>)getProperty("commentContent")).getValue();
  }
  public CommentEnd getCommentEnd() {
    return ((PropertyOne<CommentEnd>)getProperty("commentEnd")).getValue();
  }
}
