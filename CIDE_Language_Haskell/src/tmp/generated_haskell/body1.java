package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class body1 extends body {
  public body1(ArrayList<importDecl> importDecl, definitions definitions, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<importDecl>("importDecl", importDecl),
      new PropertyZeroOrOne<definitions>("definitions", definitions)
    }, firstToken, lastToken);
  }
  public body1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new body1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<importDecl> getImportDecl() {
    return ((PropertyList<importDecl>)getProperty("importDecl")).getValue();
  }
  public definitions getDefinitions() {
    return ((PropertyZeroOrOne<definitions>)getProperty("definitions")).getValue();
  }
}
