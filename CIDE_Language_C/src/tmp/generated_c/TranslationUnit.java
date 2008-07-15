package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TranslationUnit extends GenASTNode implements ISourceFile {
  public TranslationUnit(ExternalDeclaration externalDeclaration, ArrayList<ExternalDeclaration> externalDeclaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExternalDeclaration>("externalDeclaration", externalDeclaration),
      new PropertyZeroOrMore<ExternalDeclaration>("externalDeclaration1", externalDeclaration1)
    }, firstToken, lastToken);
  }
  public TranslationUnit(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TranslationUnit(cloneProperties(),firstToken,lastToken);
  }
  public ExternalDeclaration getExternalDeclaration() {
    return ((PropertyOne<ExternalDeclaration>)getProperty("externalDeclaration")).getValue();
  }
  public ArrayList<ExternalDeclaration> getExternalDeclaration1() {
    return ((PropertyZeroOrMore<ExternalDeclaration>)getProperty("externalDeclaration1")).getValue();
  }
}
