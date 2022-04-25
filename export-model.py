import torch
from torchvision import models

model = models.resnet50(pretrained=True)
model.eval()

dummy_input = torch.randn(1, 3, 224, 224)

input_names = ["actual_input"]
output_names = ["output"]

torch.onnx.export(model,
                  dummy_input,
                  "src/main/resources/resnet50.onnx",
                  verbose=False,
                  input_names=input_names,
                  output_names=output_names,
                  export_params=True,
                  )
