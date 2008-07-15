package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDeclaration extends GenASTNode {
  public TypeDeclaration(Name name, Name name1, ClassBody classBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<Name>("name1", name1),
      new PropertyOne<ClassBody>("classBody", classBody)
    }, firstToken, lastToken);
  }
  public TypeDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public Name getName1() {
    return ((PropertyZeroOrOne<Name>)getProperty("name1")).getValue();
  }
  public ClassBody getClassBody() {
    return ((PropertyOne<ClassBody>)getProperty("classBody")).getValue();
  }
}
