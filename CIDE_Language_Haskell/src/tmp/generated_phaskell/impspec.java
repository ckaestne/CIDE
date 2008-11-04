package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class impspec extends GenASTNode {
  public impspec(ASTTextNode text368, imports imports, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text368", text368),
      new PropertyZeroOrOne<imports>("imports", imports)
    }, firstToken, lastToken);
  }
  public impspec(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new impspec(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText368() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text368")).getValue();
  }
  public imports getImports() {
    return ((PropertyZeroOrOne<imports>)getProperty("imports")).getValue();
  }
}
