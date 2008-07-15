package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructDeclaratorList extends GenASTNode {
  public StructDeclaratorList(StructDeclarator structDeclarator, ArrayList<StructDeclarator> structDeclarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StructDeclarator>("structDeclarator", structDeclarator),
      new PropertyZeroOrMore<StructDeclarator>("structDeclarator1", structDeclarator1)
    }, firstToken, lastToken);
  }
  public StructDeclaratorList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructDeclaratorList(cloneProperties(),firstToken,lastToken);
  }
  public StructDeclarator getStructDeclarator() {
    return ((PropertyOne<StructDeclarator>)getProperty("structDeclarator")).getValue();
  }
  public ArrayList<StructDeclarator> getStructDeclarator1() {
    return ((PropertyZeroOrMore<StructDeclarator>)getProperty("structDeclarator1")).getValue();
  }
}
