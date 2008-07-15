package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CommentContent1 extends CommentContent {
  public CommentContent1(ASTStringNode dash, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("dash", dash)
    }, firstToken, lastToken);
  }
  public CommentContent1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CommentContent1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDash() {
    return ((PropertyOne<ASTStringNode>)getProperty("dash")).getValue();
  }
}
