package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CommentEnd2 extends CommentEnd {
  public CommentEnd2(ASTStringNode comment_end, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("comment_end", comment_end)
    }, firstToken, lastToken);
  }
  public CommentEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CommentEnd2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getComment_end() {
    return ((PropertyOne<ASTStringNode>)getProperty("comment_end")).getValue();
  }
}
