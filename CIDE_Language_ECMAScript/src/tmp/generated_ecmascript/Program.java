package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Program extends GenASTNode implements ISourceFile {
  public Program(SourceElements sourceElements, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<SourceElements>("sourceElements", sourceElements),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public Program(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Program(cloneProperties(),firstToken,lastToken);
  }
  public SourceElements getSourceElements() {
    return ((PropertyZeroOrOne<SourceElements>)getProperty("sourceElements")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
