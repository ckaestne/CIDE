package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class block extends GenASTNode {
  public block(ASTStringNode findblockcontent, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("findblockcontent", findblockcontent)
    }, firstToken, lastToken);
  }
  public block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new block(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindblockcontent() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("findblockcontent")).getValue();
  }
}
