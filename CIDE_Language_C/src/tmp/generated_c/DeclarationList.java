package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DeclarationList extends GenASTNode {
  public DeclarationList(Declaration declaration, ArrayList<Declaration> declaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Declaration>("declaration", declaration),
      new PropertyZeroOrMore<Declaration>("declaration1", declaration1)
    }, firstToken, lastToken);
  }
  public DeclarationList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DeclarationList(cloneProperties(),firstToken,lastToken);
  }
  public Declaration getDeclaration() {
    return ((PropertyOne<Declaration>)getProperty("declaration")).getValue();
  }
  public ArrayList<Declaration> getDeclaration1() {
    return ((PropertyZeroOrMore<Declaration>)getProperty("declaration1")).getValue();
  }
}
