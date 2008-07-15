package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SpecifierQualifierList1 extends SpecifierQualifierList {
  public SpecifierQualifierList1(TypeSpecifier typeSpecifier, SpecifierQualifierList specifierQualifierList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeSpecifier>("typeSpecifier", typeSpecifier),
      new PropertyZeroOrOne<SpecifierQualifierList>("specifierQualifierList", specifierQualifierList)
    }, firstToken, lastToken);
  }
  public SpecifierQualifierList1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SpecifierQualifierList1(cloneProperties(),firstToken,lastToken);
  }
  public TypeSpecifier getTypeSpecifier() {
    return ((PropertyOne<TypeSpecifier>)getProperty("typeSpecifier")).getValue();
  }
  public SpecifierQualifierList getSpecifierQualifierList() {
    return ((PropertyZeroOrOne<SpecifierQualifierList>)getProperty("specifierQualifierList")).getValue();
  }
}
