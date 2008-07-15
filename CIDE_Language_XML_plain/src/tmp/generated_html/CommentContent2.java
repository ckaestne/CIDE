package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CommentContent2 extends CommentContent {
  public CommentContent2(ASTStringNode comment_eol, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("comment_eol", comment_eol)
    }, firstToken, lastToken);
  }
  public CommentContent2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CommentContent2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getComment_eol() {
    return ((PropertyOne<ASTStringNode>)getProperty("comment_eol")).getValue();
  }
}
