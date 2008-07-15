package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PI extends GenASTNode {
  public PI(ASTStringNode pi_end, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pi_end", pi_end)
    }, firstToken, lastToken);
  }
  public PI(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PI(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPi_end() {
    return ((PropertyOne<ASTStringNode>)getProperty("pi_end")).getValue();
  }
}
