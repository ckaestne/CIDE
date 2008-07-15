package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructDeclaration extends GenASTNode {
  public StructDeclaration(SpecifierQualifierList specifierQualifierList, StructDeclaratorList structDeclaratorList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SpecifierQualifierList>("specifierQualifierList", specifierQualifierList),
      new PropertyOne<StructDeclaratorList>("structDeclaratorList", structDeclaratorList)
    }, firstToken, lastToken);
  }
  public StructDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public SpecifierQualifierList getSpecifierQualifierList() {
    return ((PropertyOne<SpecifierQualifierList>)getProperty("specifierQualifierList")).getValue();
  }
  public StructDeclaratorList getStructDeclaratorList() {
    return ((PropertyOne<StructDeclaratorList>)getProperty("structDeclaratorList")).getValue();
  }
}
