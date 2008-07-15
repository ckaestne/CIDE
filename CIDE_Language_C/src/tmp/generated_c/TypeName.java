package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeName extends GenASTNode {
  public TypeName(SpecifierQualifierList specifierQualifierList, AbstractDeclarator abstractDeclarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SpecifierQualifierList>("specifierQualifierList", specifierQualifierList),
      new PropertyZeroOrOne<AbstractDeclarator>("abstractDeclarator", abstractDeclarator)
    }, firstToken, lastToken);
  }
  public TypeName(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeName(cloneProperties(),firstToken,lastToken);
  }
  public SpecifierQualifierList getSpecifierQualifierList() {
    return ((PropertyOne<SpecifierQualifierList>)getProperty("specifierQualifierList")).getValue();
  }
  public AbstractDeclarator getAbstractDeclarator() {
    return ((PropertyZeroOrOne<AbstractDeclarator>)getProperty("abstractDeclarator")).getValue();
  }
}
