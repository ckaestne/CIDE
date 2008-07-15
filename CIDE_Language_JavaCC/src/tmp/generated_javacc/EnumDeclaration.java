package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumDeclaration extends GenASTNode {
  public EnumDeclaration(JavaIdentifier javaIdentifier, ImplementsList implementsList, EnumBody enumBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyZeroOrOne<ImplementsList>("implementsList", implementsList),
      new PropertyOne<EnumBody>("enumBody", enumBody)
    }, firstToken, lastToken);
  }
  public EnumDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public ImplementsList getImplementsList() {
    return ((PropertyZeroOrOne<ImplementsList>)getProperty("implementsList")).getValue();
  }
  public EnumBody getEnumBody() {
    return ((PropertyOne<EnumBody>)getProperty("enumBody")).getValue();
  }
}
