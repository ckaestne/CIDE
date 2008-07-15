package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ETag extends GenASTNode {
  public ETag(ASTStringNode element_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("element_id", element_id)
    }, firstToken, lastToken);
  }
  public ETag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ETag(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getElement_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("element_id")).getValue();
  }
}
