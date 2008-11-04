package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class importDecl extends GenASTNode {
  public importDecl(ASTTextNode text352, naam naam, naam naam1, hiding hiding, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text352", text352),
      new PropertyOne<naam>("naam", naam),
      new PropertyZeroOrOne<naam>("naam1", naam1),
      new PropertyZeroOrOne<hiding>("hiding", hiding)
    }, firstToken, lastToken);
  }
  public importDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new importDecl(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText352() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text352")).getValue();
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public naam getNaam1() {
    return ((PropertyZeroOrOne<naam>)getProperty("naam1")).getValue();
  }
  public hiding getHiding() {
    return ((PropertyZeroOrOne<hiding>)getProperty("hiding")).getValue();
  }
}
