package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EndTag extends GenASTNode {
  public EndTag(ASTStringNode tag_name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("tag_name", tag_name)
    }, firstToken, lastToken);
  }
  public EndTag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EndTag(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTag_name() {
    return ((PropertyOne<ASTStringNode>)getProperty("tag_name")).getValue();
  }
}
